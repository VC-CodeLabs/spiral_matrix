;; IMPORTANT!
;; Requires these dependencies.
;; Won't work without them.
;; Reach out to me if you actually want to run this code and need help.
;; :deps {org.clojure/clojure {:mvn/version "1.12.0"}
;;        net.mikera/vectorz-clj {:mvn/version "0.48.0"}}

(ns submission2
  (:require [clojure.core.matrix :as m]))

(m/set-current-implementation :vectorz)


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


;; -------------------End of original code ------------------- ;;
;; ----------------------------------------------------------- ;;

;; == Test cases ==

(defrecord TestCase [input expected-output])

(def test-cases
  [(->TestCase [[1 2 3] [4 5 6] [7 8 9]] [1 2 3 6 9 8 7 4 5])
(->TestCase [[1 2 3 4] [5 6 7 8] [9 10 11 12]] [1 2 3 4 8 12 11 10 9 5 6 7])
(->TestCase [[1]] [1])
(->TestCase [[2 3]] [2 3])
(->TestCase [[3] [2]] [3 2])
(->TestCase [[6 9 7]] [6 9 7])
(->TestCase [[7] [9] [6]] [7 9 6])
(->TestCase [[1 2] [3 4]] [1 2 4 3])
(->TestCase [[2 5] [8 4] [0 -1]] [2 5 4 -1 0 8])
(->TestCase [[2 5 8] [4 0 -1]] [2 5 8 -1 0 4])
(->TestCase [[1 2 3 4] [5 6 7 8] [9 10 11 12] [13 14 15 16] [17 18 19 20] [21 22 23 24]] [1 2 3 4 8 12 16 20 24 23 22 21 17 13 9 5 6 7 11 15 19 18 14 10])
(->TestCase [[1 2 3 4 5 6 7 8 9 10]] [1 2 3 4 5 6 7 8 9 10])
(->TestCase [[1] [2] [3] [4] [5] [6] [7] [8] [9] [10]] [1 2 3 4 5 6 7 8 9 10])
(->TestCase [[1 2 3 4] [5 6 7 8] [9 10 11 12] [13 14 15 16]] [1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10])
(->TestCase [[1 2 3 4 5] [6 7 8 9 10] [11 12 13 14 15] [16 17 18 19 20] [21 22 23 24 25]] [1 2 3 4 5 10 15 20 25 24 23 22 21 16 11 6 7 8 9 14 19 18 17 12 13])
(->TestCase [[2 3 4] [5 6 7] [8 9 10] [11 12 13]] [2 3 4 7 10 13 12 11 8 5 6 9])
(->TestCase [[2 3 4] [5 6 7] [8 9 10] [11 12 13] [14 15 16]] [2 3 4 7 10 13 16 15 14 11 8 5 6 9 12])
(->TestCase [[1 2 3 4] [5 6 7 8] [9 10 11 12] [13 14 15 16] [17 18 19 20]] [1 2 3 4 8 12 16 20 19 18 17 13 9 5 6 7 11 15 14 10])
(->TestCase [[1 2 3 4 5 6] [7 8 9 10 11 12] [13 14 15 16 17 18] [19 20 21 22 23 24] [25 26 27 28 29 30]] [1 2 3 4 5 6 12 18 24 30 29 28 27 26 25 19 13 7 8 9 10 11 17 23 22 21 20 14 15 16])
(->TestCase [[1 2 3 4 5 6 7 8 9 10] [11 12 13 14 15 16 17 18 19 20]] [1 2 3 4 5 6 7 8 9 10 20 19 18 17 16 15 14 13 12 11])
(->TestCase [[1 11] [2 12] [3 13] [4 14] [5 15] [6 16] [7 17] [8 18] [9 19] [10 20]] [1 11 12 13 14 15 16 17 18 19 20 10 9 8 7 6 5 4 3 2])
(->TestCase [[1 2 3 4 5 6 7 8 9 10] [11 12 13 14 15 16 17 18 19 20] [21 22 23 24 25 26 27 28 29 30] [31 32 33 34 35 36 37 38 39 40] [41 42 43 44 45 46 47 48 49 50] [51 52 53 54 55 56 57 58 59 60] [61 62 63 64 65 66 67 68 69 70] [71 72 73 74 75 76 77 78 79 80] [81 82 83 84 85 86 87 88 89 90] [91 92 93 94 95 96 97 98 99 100]] [1 2 3 4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 99 98 97 96 95 94 93 92 91 81 71 61 51 41 31 21 11 12 13 14 15 16 17 18 19 29 39 49 59 69 79 89 88 87 86 85 84 83 82 72 62 52 42 32 22 23 24 25 26 27 28 38 48 58 68 78 77 76 75 74 73 63 53 43 33 34 35 36 37 47 57 67 66 65 64 54 44 45 46 56 55])
(->TestCase [[1 2 3 4 5] [6 7 8 9 10] [11 12 13 14 15]] [1 2 3 4 5 10 15 14 13 12 11 6 7 8 9])
(->TestCase [[23 18 20 26 25] [24 22 3 4 4] [15 22 2 24 29] [18 15 23 28 28]] [23 18 20 26 25 4 29 28 28 23 15 18 15 24 22 3 4 24 2 22])
(->TestCase [[1 2 3] [4 6 0] [7 8 9]] [1 2 3 0 9 8 7 4 6])
(->TestCase [[1 11] [2 0] [3 13] [0 14] [5 0] [6 16] [0 0] [8 18] [9 19] [10 20]] [1 11 0 13 14 0 16 0 18 19 20 10 9 8 0 6 5 0 3 2])])

(def all-tests-passed (atom true))

(doseq [[i test-case] (map-indexed vector test-cases)]
  (let [output (unspiral (:input test-case))]
    (if (= output (:expected-output test-case))
      (println (str "Test " (inc i) " passed."))
      (do (println (str "Test " (inc i) " failed. Expected: " (:expected-output test-case) ", but got: " output))
          (reset! all-tests-passed false)))))

(when @all-tests-passed
  (let [start-time (System/currentTimeMillis)]
    (dotimes [_ 10000]
      (doseq [test-case test-cases]
        (unspiral (:input test-case))))
    (let [end-time (System/currentTimeMillis)]
      (println (str "All tests passed. Time for 10000 iterations: " (- end-time start-time) " ms")))))
