(ns sunrise.dom
  (:require [reagent.core :as reagent]
            [sunrise.state :as state]
            [sunrise.component :as component]))

(defn get-window-dimensions []
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    {:width width :height height}))


(defn init! []
  (.addEventListener js/window
                     "resize"
                     #(state/update-window! (get-window-dimensions)))
  (state/update-window! (get-window-dimensions))
  (reagent/render-component [component/app-container] (.getElementById js/document "app")))
