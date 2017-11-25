(ns sunrise.core
  (:require [reagent.core :as r]
            [goog.dom]
            [goog.style]))

(def by-id goog.dom.getElement)
(def bounds goog.style.getBounds)

(defn line-dy [base]
  (- (rand base)
     (/ base 2)))

(defn line-dx [base]
  (/ 25 2))

(defn line-delta [dx dy]
  (str "l " dx " " dy))

(defn ridge-line [n base]
  (let [dys (repeatedly n #(line-dy base))
        dxs (repeatedly n #(line-dx base))
        line-samples (map line-delta dxs dys)]
    (clojure.string/join " " line-samples)))

(defn mountain-path [n width height base]
  (str "M 0 193 "
       (ridge-line n base)
       "L " width " 193"
       "V " height
       "L 0 " height
       "Z"))

(defn app-container []
  (let [rect (-> (by-id "app")
                 bounds)
        width (.-width rect)
        height (.-height rect)
        n 60
        base 10]
    (fn []
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
                 :offset "100%"}]
         ]]
       [:g.skybox {}
        [:rect.sky {:width "100%"
                    :height "50%"
                    :y "0"
                    :x "0"}]
        [:circle.sun {:r "10%"
                      :cx "40%"
                      :cy "30%"}]]
       [:g.foreground {}
        [:path.mountain-1
         {:d (mountain-path n width height base)}]]])))

(r/render-component [app-container] (by-id "app"))
