##Find the nth smallest element from two arrays

Let `A[1..n]` and `B[1..n]` be two arrays of distinct integers, each sorted in an increasing order. Find the `nth` smallest of the `2n` combined elements. Your program must run in `O(log n)`` time.

###Solution

One approach is to use a modified version of the binary search to get `O(logn)` time. Assume `A` and `B` have 4 elements (Assume index starts from 1). Start by compare middle elements, we can assign index `i =2` and `j = 2`. From our indexes, `A[i] = 4` and `B[j] = 5`

```
A = {1,4,6,7}, A[i]= 4, A[i+1] = 6
B = {2,5,8,9}, B[j] = 5
```

If we compare `A[i]  with `B[j] , we know that `A[i]< B[j]`, but if we compare `B[j]` with `A[i+1]`, we know that `B[j]<A[i+1]`. 5 is greater than `A = 1, 4 & B = 2`. Hence, `B[j]` which is equal to 5, is at least greater than `A[1…i]` and `B[1…j-1]`.

Since both arrays are sorted, we know from indexes, that `B[j]` is greater than `(i) +(j - 1)` elements, and is the `(i) +(j - 1) + 1` smallest. `B[j]`, therefore, is the `(2) + (2-1) + 1 = 4th` smallest and the answer.

If we want to know the nth smallest element, we will have to search for an index `i` in `A` and find an index `j` in `B` so that `i + j = n` (we want to know the nth smallest defined by our equation).

However, it could be possible that `A[i]` and `A[i + 1]` is smaller than `B[j]`. Consider the following example.

```
A = {2,5,8,13},  A[i]= 5, A[i+1] = 8
B = {1,9,10,15}, B[j] = 9
```

Start with middle element, `i = 2`. Since `j = n – i`, `j = 2`, then both `A[i]` and `A[i+1] are smaller than `B[j]`. In this  case, `B[j] is higher than the `nth` smallest element. Therefore, we have to recursively check the upper half. Conversely, if `B[j] and `B[j+1] are smaller than `A[i]`, then we recursively check the lower half.

A few special cases have to be handled.

* Case 1: If `A[n] < B[1]`, we know that `A[n]` is the `nth` number.
* Case 2: If `B[n] < A[1]`, we know that `B[n]` is the `nth` number.
* Case 3: `i + j = n`

If neither case 1 nor case 2 holds true, then we have to find the index of `i` and index of `j` recursively so that our equation `i + j = n` holds true (Case 3).
