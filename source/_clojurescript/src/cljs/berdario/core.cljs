(ns berdario.core
  (:require [domina :as dom]
            [domina.css :refer [sel]]
            [domina.events :as ev]))

(defn get-nav 
  (let [main-nav (dom/insert-before!
                 (sel "ul.main-navigation, ul[role=main-navigation]")
                 "<fieldset class=\"mobile-nav\">")
        mobile-nav (dom/append!
                   (sel "fieldset.mobile-nav")
                   "<select>")
        add-option #(
                     (dom/append! (sel mobile-nav "select") (str "<option value" (.-href %) (.-text %))))]
    (doall (map add-option (nodes (sel main-nav "a"))))
    )

)

(defn add-sidebar-toggler []
  (when-not (dom/has-class? (sel "body") "sidebar-footer")
    )
)
