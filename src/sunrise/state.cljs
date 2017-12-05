(ns sunrise.state
  (:require [reagent.core :as reagent]))

(def display-state (reagent/atom {}))

(defn update-window! [dimensions]
   (swap! display-state assoc :window dimensions))
