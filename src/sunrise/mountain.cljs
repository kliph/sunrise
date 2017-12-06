(ns sunrise.mountain)

(defn line-dy [base]
  (- (rand base)
     (/ base 2)))

(defn line-dx [base]
  (/ 25 2))

(defn line-delta [dx dy]
  (str "l " dx " " dy))

(defn ridge-line [{:keys [n-points base]}]
  (let [dys (->> (repeatedly (* 2 n-points) #(line-dy base))
                 (partition 2)
                 (map (fn [[a b]]
                        (* 0.5 (+ a b)))))
        dxs (repeatedly n-points #(line-dx base))
        line-samples (map line-delta dxs dys)]
    (clojure.string/join " " line-samples)))

(defn mountain-path [{:keys [n-points width height base nth-mountain] :as params}]
  (let [y (+ (* height 0.4) (* nth-mountain 30))]
    (str "M 0 "
         y
         (ridge-line params)
         "L " width " "
         y
         "V " height
         "L 0 " height
         "Z")))

(defn mountains [{:keys [n-points width height base n-mountains] :as params}]
  (map
   (fn [x]
     (let [name (str "mountain-" x)]
       [:path
        {:className name
         :key name
         :d (mountain-path (-> params
                               (assoc :nth-mountain x)))
         :opacity 0.5}]))
   (range n-mountains)))
