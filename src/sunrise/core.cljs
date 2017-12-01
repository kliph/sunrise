(ns sunrise.core
  (:require [reagent.core :as reagent]
            [sunrise.component :as component]
            [sunrise.state]))

(.addEventListener js/window "resize" #(swap! sunrise.state/display-state assoc :window (sunrise.state/get-window-dimensions)))

(reagent/render-component [component/app-container] (.getElementById js/document "app"))
