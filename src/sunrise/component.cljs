(ns sunrise.component
  (:require [reagent.core :as reagent]
            [sunrise.state]
            [sunrise.mountain :as mountain]))

(defn app-container []
  (let [state @sunrise.state/display-state
        width (get-in state [:window :width])
        height (get-in state [:window :height])
        ridge-line-points-per-pixel 0.15
        n-points (-> (* width ridge-line-points-per-pixel)
                     Math/floor
                     int)
        base 10
        n-mountains 4]
    [:svg {:xmlns "http://www.w3.org/2000/svg"
           :width "100%"
           :height "100%"}
     [:defs {}
      [:linearGradient#skyGradient {:x1 "0"
                                    :y1 "0"
                                    :x2 "0"
                                    :y2 "1"}
       [:stop {:className "stop1"
               :offset "0%"
               :stop-opacity "0"}]
       [:stop {:className "stop2"
               :offset "60%"}]]]
     [:g.skybox {}
      [:rect.sky {:width "100%"
                  :height "100%"
                  :y "0"
                  :x "0"}]
      [:circle.sun {:r "10%"
                    :cx "40%"
                    :cy "25%"}]]
     [:g.foreground {}
      (mountain/mountains n-points width height base n-mountains)]]))
