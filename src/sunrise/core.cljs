(ns sunrise.core
  (:require [reagent.core :as reagent]
            [sunrise.component :as component]))

(defn get-window-dimensions []
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    {:width width :height height}))

(def display-state (reagent/atom {:window (get-window-dimensions)}))

(.addEventListener js/window "resize" #(swap! display-state assoc :window (get-window-dimensions)))
(reagent/render-component [component/app-container] (.getElementById js/document "app"))
