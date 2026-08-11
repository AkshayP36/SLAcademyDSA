Architected visual sweep strategy to illuminate dynamic programming passes

Let's build the intuition slowly, like a story.

The setup

You're picking three spots in the array, called i, j, k — but there's a rule: they must come in order, i first, j second (same place or later), k third (same place or later). At each spot you multiply the number there by a fixed weight (B, C, D) and add everything up. You want the biggest possible total.

Where's the "recursion"? Think in stages, not spots.

The trick is to stop thinking "which three positions do I pick" and instead think "what's the best I could possibly be doing by the time I reach position x, if x is my last pick so far."

Imagine three friends — Ben, Cara, Dev — standing in a line that only moves forward (left to right through the array). Ben always goes first, Cara can only stand at or after Ben, Dev only at or after Cara.

Ben's job: as we walk along the array, Ben keeps whispering "the best A[x]*B I've seen yet" — a running record. He never looks back.
Cara's job: at every step, Cara asks "what's the best score if I stop RIGHT HERE, using Ben's best so far, plus my own A[x]*C?" She also keeps a running record of the best combined score seen so far.
Dev's job: same idea — at every step, "what's the best if I stop here, using Cara's best-so-far, plus my own A[x]*D?"
That's the whole "recursion": each friend's best score depends only on the previous friend's best score up to that point — a classic overlapping-subproblem structure, just solved by scanning left to right instead of writing an explicit recursive function.

Walking through your example (this is exactly what the diagram above shows):

A = [1, 5, -3, 4, -2], B=2, C=1, D=-1

Pass 1 (Ben, tracks best A[x]*B): running best = 2, 10, 10, 10, 10
Pass 2 (Cara, adds A[x]*C to Ben's running best, keeps her own running best): 2+1=3, 10+5=15, 10-3=7→still 15, 10+4=14→still 15, 10-2=8→still 15
Pass 3 (Dev, adds A[x]*D to Cara's running best, keeps his own running best):
x=1: 3 + 1×(-1) = 2
x=2: 15 + 5×(-1) = 10
x=3: 15 + (-3)×(-1) = 18 ← new best!
x=4: 15 + 4×(-1) = 11
x=5: 15 + (-2)×(-1) = 13
running best: 2, 10, 18, 18, 18
Final answer: 18 — matching the expected output, achieved at k=3 (with j=2, i=2 baked into Cara's running best from position 2).

Why this works (the DP insight)

At every position, each friend only needs one number: "the best total I could have achieved ending at or before this spot." They never need to remember which index gave them that number — just the value. That's why one left-to-right sweep, carrying forward a single "best so far," replaces what looks like a 3-nested-loop search over all i≤j≤k triples.

The code

python
def solve(A, B, C, D):
best1 = float('-inf')   # best A[i]*B so far
best2 = float('-inf')   # best best1 + A[j]*C so far
best3 = float('-inf')   # best best2 + A[k]*D so far

    for x in A:
        best1 = max(best1, x * B)
        best2 = max(best2, best1 + x * C)
        best3 = max(best3, best2 + x * D)

    return best3

Let's build the intuition slowly, like a story.

The setup

You're picking three spots in the array, called i, j, k — but there's a rule: they must come in order, i first, j second (same place or later), k third (same place or later). At each spot you multiply the number there by a fixed weight (B, C, D) and add everything up. You want the biggest possible total.

Where's the "recursion"? Think in stages, not spots.

The trick is to stop thinking "which three positions do I pick" and instead think "what's the best I could possibly be doing by the time I reach position x, if x is my last pick so far."

Imagine three friends — Ben, Cara, Dev — standing in a line that only moves forward (left to right through the array). Ben always goes first, Cara can only stand at or after Ben, Dev only at or after Cara.

Ben's job: as we walk along the array, Ben keeps whispering "the best A[x]*B I've seen yet" — a running record. He never looks back.
Cara's job: at every step, Cara asks "what's the best score if I stop RIGHT HERE, using Ben's best so far, plus my own A[x]*C?" She also keeps a running record of the best combined score seen so far.
Dev's job: same idea — at every step, "what's the best if I stop here, using Cara's best-so-far, plus my own A[x]*D?"
That's the whole "recursion": each friend's best score depends only on the previous friend's best score up to that point — a classic overlapping-subproblem structure, just solved by scanning left to right instead of writing an explicit recursive function.

Walking through your example (this is exactly what the diagram above shows):

A = [1, 5, -3, 4, -2], B=2, C=1, D=-1

Pass 1 (Ben, tracks best A[x]*B): running best = 2, 10, 10, 10, 10
Pass 2 (Cara, adds A[x]*C to Ben's running best, keeps her own running best): 2+1=3, 10+5=15, 10-3=7→still 15, 10+4=14→still 15, 10-2=8→still 15
Pass 3 (Dev, adds A[x]*D to Cara's running best, keeps his own running best):
x=1: 3 + 1×(-1) = 2
x=2: 15 + 5×(-1) = 10
x=3: 15 + (-3)×(-1) = 18 ← new best!
x=4: 15 + 4×(-1) = 11
x=5: 15 + (-2)×(-1) = 13
running best: 2, 10, 18, 18, 18
Final answer: 18 — matching the expected output, achieved at k=3 (with j=2, i=2 baked into Cara's running best from position 2).

Why this works (the DP insight)

At every position, each friend only needs one number: "the best total I could have achieved ending at or before this spot." They never need to remember which index gave them that number — just the value. That's why one left-to-right sweep, carrying forward a single "best so far," replaces what looks like a 3-nested-loop search over all i≤j≤k triples.

The code

python
def solve(A, B, C, D):
    best1 = float('-inf')   # best A[i]*B so far
    best2 = float('-inf')   # best best1 + A[j]*C so far
    best3 = float('-inf')   # best best2 + A[k]*D so far
    
    for x in A:
            best1 = max(best1, x * B)
            best2 = max(best2, best1 + x * C)
            best3 = max(best3, best2 + x * D)
    
    return best3





















