(ns berdario.github
  (:require
   [berdario.core :as core]
   [dommy.core :as dommy]
   [shoreleave.remote :refer [request]])
  (:use-macros
   [dommy.macros :only [node sel sel1]]))


(defn render [target repos]
  (dommy/set-html! (sel1 target)
                   (node(for [repo repos]
                          [:li [:a {:href (.-html_url repo)} (.-name repo)] [:p (core/escape-html (.-description repo))]]))))

(defn ^:export showRepos [{:keys [user count skip_forks target]}]
  (let [url (format "https://api.github.com/users/%s/repos?sort=pushed" user)
        handler (fn [{:keys [id body status event]}]
                  (let [repos (if skip_forks
                                (filter (comp not .-fork) body)
                                body)]
                    (render target (take count repos)))
)]
    (request url {:on-success handler}))
  )
