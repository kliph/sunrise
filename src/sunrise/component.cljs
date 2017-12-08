(ns sunrise.component
  (:require [reagent.core :as reagent]
            [sunrise.state]
            [sunrise.mountain :as mountain]
            [sunrise.skybox :as skybox]))

(defn app-container []
  (let [state @sunrise.state/display-state
        width (get-in state [:window :width])
        height (get-in state [:window :height])
        ridge-line-points-per-pixel 0.15
        n-points (-> (* width ridge-line-points-per-pixel)
                     Math/floor
                     int)
        base 10
        n-mountains 4
        mountain-params {:n-points n-points
                         :width width
                         :height height
                         :base base
                         :n-mountains n-mountains}]
    [:svg {:xmlns "http://www.w3.org/2000/svg"
           :width "100%"
           :height "100%"}
     (skybox/skybox)
     [:g.foreground {}
      (mountain/mountains mountain-params)]]))
