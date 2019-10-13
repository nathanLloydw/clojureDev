(ns projects.projectFourA)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[imports.ops_search :refer :all])
(use '[imports.a-star-search :refer :all])
(use '[imports.breadth-search :refer :all])

(def busRoutes
  '{ :Newcastle ({:location "middlesbrough" :cost 5} {:location "darlington" :cost 4} {:location "durham" :cost 4})
    :middlesbrough ({:location "redcar" :cost 2} {:location "hartlepool" :cost 2} {:location "northallerton" :cost 3} {:location "newcastle" :cost 5})
    :darlington ({:location "newcaslte" :cost 6} {:location "middlesbrough" :cost 3} {:location "durham" :cost 4} {:location "northallerton" :cost 2} {:location "leeds" :cost 7})
    :durham ({:location "darlington" :cost 3} {:location "newcastle" :cost 6})
    :northallerton ({:location "durham" :cost 4} {:location "darlington" :cost 5})
    :leeds ({:location "manchester" :cost 8} {:location "york" :cost 6} {:location "wakefield" :cost 3})
    :manchester ({:location "liverpool" :cost 9} {:location "warrington" :cost 3} {:location "bolton" :cost 4})
    :liverpool ( {:location "warrington" :cost 2} {:location "manchester" :cost 7} {:location "chester" :cost 4})
    })



(defn a*lmgA [state]
  (let [n (:state state)
        c (:cost state)]
    (list
      {:state (+ n 1), :cost (+ c 2)}
      {:state (+ n 5), :cost (+ c 7)}
      {:state (* n 2), :cost (+ c 1)}
      )))

(defn a*lmgB [state]
  (let [location (:location state)
        cost (:cost state)]
    (for [{loc :location cos :cost} (busRoutes (keyword location))]  (hash-map :cost (+ cos cost) :state loc))))

