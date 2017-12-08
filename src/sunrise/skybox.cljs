(ns sunrise.skybox
  (:require [sunrise.sky :as sky]
            [sunrise.sun :as sun]))

(defn skybox []
  [:g.skybox {}
   (sky/sky)
   (sun/sun)])
