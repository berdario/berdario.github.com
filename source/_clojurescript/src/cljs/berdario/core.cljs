(ns berdario.core
  (:require
   [dommy.core :as dommy])
  (:use-macros
   [dommy.macros :only [node sel sel1]]))

(defn get-nav []
  (let [main-nav (dommy/insert-before!
                 (sel "ul.main-navigation, ul[role=main-navigation]")
                 "<fieldset class=\"mobile-nav\">")
        mobile-nav (dommy/append!
                   (sel "fieldset.mobile-nav")
                   "<select>")
        add-option #(dommy/append! (sel mobile-nav "select") 
                                    (node [:option {:value (.-href %)} (str  "&raquo; " (.-text %))]))
        redirect (fn [ev]
                   (when-let [target (.-value (.-target ev))]
                     (set! (.-location js/window) target)))]
    (dommy/append! (sel mobile-nav "select") (node [:option "Navigate&hellip;"] ))
    (doall (map add-option (concat (sel main-nav "a") (sel "ul.subscription a"))))
    (dommy/listen! (sel1 mobile-nav "select") :change redirect)
    )
)

(defn add-sidebar-toggler []
  (when-not (dommy/has-class? (sel "body") "sidebar-footer")
    (dommy/append! (sel1 "#content") (node [:span.toggle-sidebar]))
    (dommy/listen! (sel1 ".toggle-sidebar") :click
                   (fn [ev]
                     (.preventDefault ev)
                     (if (dommy/has-class? (sel "body"))
                       (.removeClass (sel "body") "collapse-sidebar")
                       (.addClass (sel "body") "collapse-sidebar"))))
    (let [sections (sel "aside.sidebar > section")]
      (doall (map .addClass (cons :first (cycle [:even :odd]))))
      (when (count sections)
        (.addClass (sel "aside.sidebar") "thirds")))
    )
)

(comment (defn add-code-line-numbers []
   (letfn [(add-numbers [code]
             (let [total-lines (inc (count (sel code ".line")))
                   line-numbers (for [i (range 1 total-lines)]
                                  [:span.line-number i])
                   new-code (node [:table [:tbody [:tr [:td.gutter [:pre.line-numbers line-numbers]] [:td.code (sel1 code :pre)]]]])]
               (dommy/set-html! (sel1 :code) new-code)))]
     (doall (map add-numbers (sel :div.gist-highlight))))))


(defn escape-html [text]
  (dommy/html (dommy/set-text! (node [:div]) text)))


(defn ^:export init []
  (get-nav)
  (add-sibebar-toggler))
