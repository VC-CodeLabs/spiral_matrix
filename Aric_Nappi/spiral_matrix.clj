(ns spiral-matrix)

;; Define matrix here
(def m [[1  2  3  4]
        [5  6  7  8]
        [9  10 11 12]
        [13 14 15 16]])


;; Function definitions

(defn butfirstlast
  "All but the first and last entries of a vector."
  [v]
  (subvec v 1 (dec (count v))))

(defn firstrow
  "First row of a matrix."
  [m]
  (first m))

(defn lastcol
  "Last column of a matrix."
  [m]
  (mapv peek m))

(defn lastrow
  "Last row of a matrix."
  [m]
  (peek m))

(defn firstcol
  "First column of a matrix."
  [m]
  (mapv first m))

(defn border
  "The border elements of a matrix in clockwise order."
  [m]
  (reduce into
          [(firstrow m)
           (butfirstlast (lastcol m))
           (rseq (lastrow m))
           (rseq (butfirstlast (firstcol m)))]))

(defn interior
  "The matrix without its border elements."
  [m]
  (mapv butfirstlast (butfirstlast m)))

(defn unspiral
  "The elements of an 'unspiraled' matrix."
  [m]
  (if (empty? m)
    []
    (concat (border m) (unspiral (interior m)))))


;; Evaluate `unspiral` on the matrix `m`. Print value.
(println (unspiral m))
