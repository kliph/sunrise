(ns sunrise.core
  (:require [reagent.core :as reagent]
            [sunrise.component :as component]
            [sunrise.state :as state]))

(defn get-window-dimensions []
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    {:width width :height height}))

(defn update-window-state! []
  (state/update-window! (get-window-dimensions)))

(.addEventListener js/window "resize" update-window-state!)

(update-window-state!)
(reagent/render-component [component/app-container] (.getElementById js/document "app"))
