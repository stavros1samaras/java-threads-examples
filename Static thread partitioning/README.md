🧾 MATRIXADDITIONPARALLEL
This program adds two matrices using static thread partitioning.
It divides the work by rows, assigning equal blocks to each thread.
Useful for understanding how matrix operations scale across multiple CPU cores.

🧾 VECTORADDITIONPARALLEL
A simple example of adding two large vectors in parallel.
Each thread handles a specific portion of the array using static indexing.
This demonstrates efficient parallelization for 1D data structures.

🧾 RGBTOGRAYSCALEPARALLEL
Converts a color image to grayscale using multiple threads.
The image is divided by horizontal blocks (rows), and each thread processes its region.
A practical example of using static threads for image processing tasks.
