(ns sunrise.component
  (:require [reagent.core :as reagent]
            [sunrise.state]))

(defn line-dy [base]
  (- (rand base)
     (/ base 2)))

(defn line-dx [base]
  (/ 25 2))

(defn line-delta [dx dy]
  (str "l " dx " " dy))

(defn ridge-line [n-points base]
  (let [dys (->> (repeatedly (* 2 n-points) #(line-dy base))
                 (partition 2)
                 (map (fn [[a b]]
                        (* 0.5 (+ a b)))))
        dxs (repeatedly n-points #(line-dx base))
        line-samples (map line-delta dxs dys)]
    (clojure.string/join " " line-samples)))

(defn mountain-path [n-points width height base nth-mountain]
  (let [y (+ (* height 0.4) (* nth-mountain 30))]
    (str "M 0 "
         y
         (ridge-line n-points base)
         "L " width " "
         y
         "V " height
         "L 0 " height
         "Z")))

(defn mountains [n-points width height base n-mountains]
  (map
   (fn [x]
     (let [name (str "mountain-" x)]
       [:path
        {:className name
         :key name
         :d (mountain-path n-points width height base x)
         :opacity 0.5}]))
   (range n-mountains)))

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
      (mountains n-points width height base n-mountains)]]))
