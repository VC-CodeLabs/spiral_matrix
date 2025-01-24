;; IMPORTANT!
;; Requires these dependencies.
;; Won't work without them.
;; Reach out to me if you actually want to run this code and need help.
;; :deps {org.clojure/clojure {:mvn/version "1.12.0"}
;;        net.mikera/vectorz-clj {:mvn/version "0.48.0"}}

(ns spiral-matrix2
  (:require [clojure.core.matrix :as m]))

(m/set-current-implementation :vectorz)


;; Define matrix here
(def M (m/matrix [[1  2  3  4]
                  [5  6  7  8]
                  [9  10 11 12]
                  [13 14 15 16]]))

;; Implementation
(defn exchange-matrix
  "An nxn matrix with 1's on the anti-diagonal."
  [n]
  (m/compute-matrix
   [n n]
   (fn [i j]
     (if (= (+ i j) (dec n)) 1 0))))

(defn rotated-matrix
  "A matrix M 'rotated' counterclockwise 90 degrees."
  [M]
  ; (M*E)^T
  (m/transpose (m/mmul M (exchange-matrix (m/column-count M)))))

(defn unspiraled
  "A vector representing an 'unspiraled' version of a matrix M."
  [M]
  (let [first-row (m/get-row M 0)]
    (if (= 1 (m/row-count M))
      first-row
      (let [but-first-row (m/submatrix M [[1 (dec (m/row-count M))]])
            rotated (rotated-matrix but-first-row)]
        (m/join first-row (unspiraled rotated))))))


;; Print answer here.
(println (map int (unspiraled M)))


;; Test cases
;; (= (m/matrix [1 2 3 4]) (unspiraled-matrix (m/matrix [[1 2 3 4]])))

;; (= (m/matrix [1 2 3 4]) (unspiraled-matrix (m/matrix [[1] [2] [3] [4]])))

;; (= (m/matrix [1 2 4 3]) (unspiraled-matrix (m/matrix [[1 2] [3 4]])))

;; (= (m/matrix [1 2 3 6 9 8 7 4 5]) (unspiraled-matrix (m/matrix [[1 2 3]
;;                                                                 [4 5 6]
;;                                                                 [7 8 9]])))

;; (= (m/matrix [1 2 3 4 5 10 15 20 19 18 17 16 11 6 7 8 9 14 13 12])
;;    (unspiraled-matrix (m/matrix [[1  2  3  4  5]
;;                                  [6  7  8  9  10]
;;                                  [11 12 13 14 15]
;;                                  [16 17 18 19 20]])))

;; (= (m/matrix [1 2 4 6 5 3])
;;    (unspiraled-matrix (m/matrix [[1 2]
;;                                  [3 4]
;;                                  [5 6]])))

;; (= (m/matrix [1 2 3 4 8 7 6 5])
;;    (unspiraled-matrix (m/matrix [[1 2 3 4]
;;                                  [5 6 7 8]])))
