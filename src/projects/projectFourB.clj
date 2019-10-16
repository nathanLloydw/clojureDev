(ns projects.projectFourB)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[imports.ops_search :refer :all])
(use '[imports.a-star-search :refer :all])
(use '[imports.breadth-search :refer :all])

(def busRoutes
  '{ :Newcastle ({:location "middlesbrough" :cost 8} {:location "darlington" :cost 7} {:location "durham" :cost 6})
    :middlesbrough ({:location "redcar" :cost 2} {:location "hartlepool" :cost 2} {:location "northallerton" :cost 3} {:location "Newcastle" :cost 5} {:location "leeds" :cost 5})
    :darlington ({:location "Newcastle" :cost 6} {:location "middlesbrough" :cost 3} {:location "durham" :cost 4} {:location "northallerton" :cost 2} {:location "leeds" :cost 17})
    :durham ({:location "darlington" :cost 3} {:location "Newcastle" :cost 6})
    :northallerton ({:location "durham" :cost 4} {:location "darlington" :cost 5})
    :leeds ({:location "manchester" :cost 18} {:location "york" :cost 6} {:location "wakefield" :cost 3})
    :manchester ({:location "liverpool" :cost 19} {:location "warrington" :cost 3} {:location "bolton" :cost 4})
    :liverpool ( {:location "warrington" :cost 2} {:location "manchester" :cost 7} {:location "chester" :cost 4})
    })

(defn a*lmgB [state]
  (let [location (:state state)
        cost (:cost state)]
    (for [{loc :location cos :cost} (busRoutes (keyword location))]  (hash-map :cost (+ cos cost) :state loc))))

;; (A*search {:state "Newcastle" :cost 0} "chester" a*lmgB)

(defn allStations []
  (count (set (flatten (for [x busRoutes] (for [{loc :location cos :cost} (second x) ] loc))))))

(defn removeBeenValues [states toRemove]
  (remove nil? (for [ {state :state cos :cost} states] (if (some #(= state %)toRemove) nil (hash-map :cost cos :state state)))))

(defn fixPrice [currentLoc previousLoc]
  (first (remove nil? (for [{loc :location cost :cost} (busRoutes (keyword previousLoc))] (if (= loc currentLoc) cost )))))

(defn best-firstSearch
  ([state goal lmg]
   (best-firstSearch state goal lmg []))
  ([state goal lmg been]
   (cond
     (= (:state state) goal )
         (do (conj been (:state state)) state)
     (= (count been) allStations)
         false
     (empty? (removeBeenValues (lmg state) been))
         (do (println state " - FAIL")
             (best-firstSearch {:state (last been) :cost (- (state :cost) (fixPrice (:state state) (last been)))} goal lmg (conj been (:state state))))
     :else
     (do (println state)
         (best-firstSearch (first (sort-by :cost (removeBeenValues (lmg state) been))) goal lmg (conj been (:state state)))))))

;;(best-firstSearch {:state "Newcastle" :cost 0} "chester" a*lmgB)