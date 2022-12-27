# Java Quantum Computer Simulator

**REQUIRES APACHE COMMONS MATH**

Note: for this to work, all of the files must be in a source directory. The easiest way to do this is to put all of the files in one folder (that is what I do). You do not need to (read: shouldn't) run anything in "Core" or "Gates" (but the files do need to be in the source directory).

Since this is a quantum computer SIMULATOR, everything here will be (necessarily by mathematics) incredibly slow. For n qubits, a matrix of size 2^n x 2^n is created and manipulated which is fine for n<7 or so. After that, the speed problems get incredibly noticeable. Even for lousy things such as superdense coding.

There may be improvements in speed here and there but _as far as I know_, with this program being able to work at runtime, there aren't signficant speed nor memory enhancements to make.

As you may see, the Shor's Algorithm program is not entirely complete :'(. I was having difficulties finding a good construction of exp mod n that was both general and memory conservative. I believe I have found one. However, since it takes a while to implement, I have decided to first upload this here.

[Dec 2022 Notice]: This project is, for all practical purposes, entirely obsolete—it belongs to 2017. Though technically complete, the project has quite a few design flaws.
