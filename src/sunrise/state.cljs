(ns sunrise.state
  (:require [reagent.core :as reagent]))

(defn get-window-dimensions []
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    {:width width :height height}))

(def display-state (reagent/atom {:window (get-window-dimensions)}))
