(ns spiral-matrix)

;; Define matrix here
(def m [[1  2  3  4]
        [5  6  7  8]
        [9  10 11 12]
        [13 14 15 16]])


;; Function definitions

(defn butfirstlastv
  "All but the first and last entries of a vector."
  [v]
  (let [v-count (count v)]
    (if (< v-count 3)
      []
      (subvec v 1 (dec v-count)))))

(defn butfirstv
  "All entries but the first of a vector."
  [v]
  (let [v-count (count v)]
    (if (< v-count 2)
      []
      (subvec v 1))))

(defn butlastv
  "All entries but the last of a vector."
  [v]
  (let [v-count (count v)]
    (if (< v-count 2)
      []
      (subvec v 0 (dec v-count)))))

(defn firstrow
  "First row of a matrix as a vector."
  [m]
  (first m))

(defn lastcol
  "Last column of a matrix as a vector."
  [m]
  (let [c (mapv peek m)]
    (when (seq c) c)))

(defn lastrow
  "Last row of a matrix as a vector."
  [m]
  (peek m))

(defn firstcol
  "First column of a matrix as a vector."
  [m]
  (let [c (mapv first m)]
    (when (seq c) c)))

(defn reversev
  "Reverse of a vector."
  [v]
  (vec (rseq v))) ; `rseq` is more performant on vectors than `reverse`

(defn butfirstrow
  "A matrix m but without the first row."
  [m]
  (butfirstv m))

(defn butlastcol
  "A matrix but without the last column."
  [m]
  (if (> (count (firstrow m)) 1)
    (mapv butlastv m)
    []))

(defn border
  "The border elements of a matrix in clockwise order,
     starting from the top-left entry."
  [m]
  (let [row-first (firstrow m)
        m1 (butfirstrow m)
        col-last (lastcol m1)
        m2 (butlastcol m1)
        row-last (let [lr (lastrow m2)] (when lr (reversev lr)))
        m3 (butlastv m2)
        col-first (let [fc (firstcol m3)] (when fc (reversev fc)))]
    (reduce into [row-first col-last row-last col-first])))

(defn interior
  "The matrix without its border elements."
  [m]
  (let [row-count (count m)
        col-count (count (firstrow m))]
    (if (and (> row-count 2) (> col-count 2))
      (mapv butfirstlastv (butfirstlastv m))
      [])))

(defn unspiral
  "The elements of an 'unspiraled' matrix."
  [m]
  (if (empty? m)
    []
    (into (border m) (unspiral (interior m)))))


;; Evaluate `unspiral` on the matrix `m`.
(unspiral m)


;; == Test cases ==
;; (= [] (unspiral []))

;; (= [1 2 3 4] (unspiral [[1 2 3 4]]))

;; (= [1 2 3 4] (unspiral [[1] [2] [3] [4]]))

;; (= [1 2 4 3] (unspiral [[1 2] [3 4]]))

;; (= [1 2 3 6 9 8 7 4 5] (unspiral [[1 2 3]
;;                                   [4 5 6]
;;                                   [7 8 9]]))

;; (= [1 2 3 4 5 10 15 20 19 18 17 16 11 6 7 8 9 14 13 12]
;;    (unspiral [[1  2  3  4  5]
;;               [6  7  8  9  10]
;;               [11 12 13 14 15]
;;               [16 17 18 19 20]]))

;; (= [1 2 4 6 5 3]
;;    (unspiral [[1 2]
;;               [3 4]
;;               [5 6]]))

;; (= [1 2 3 4 8 7 6 5]
;;    (unspiral [[1 2 3 4]
;;               [5 6 7 8]]))
