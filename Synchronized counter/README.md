🧾 SYNCHRONIZEDCOUNTERGLOBAL
A basic example using synchronized(lock) to protect access to a shared global array.
Each thread loops with a nested loop and updates array values safely inside the critical section.
Demonstrates how synchronized replaces manual ReentrantLock handling.

🧾 SYNCHRONIZEDCOUNTERWITHCLASS
This version wraps the shared state in a class and applies synchronized blocks to all methods.
It prevents race conditions while keeping the logic clean and encapsulated.
It’s a structured alternative to locking on raw objects.

🧾 SYNCHRONIZEDGLOBALCOUNTER
Threads increment a global counter and use it as an index into an array.
Access is synchronized using a global lock object.
Prevents out-of-bounds errors and shows how multiple resources can be guarded together.

🧾 SYNCHRONIZEDGLOBALCOUNTERWITHCLASS
The shared counter and array are encapsulated in a class.
All read/write methods are synchronized to ensure thread-safe access.
Threads perform a while(true) loop and increment both safely — a complete, object-oriented sync model.
