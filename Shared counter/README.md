🧾 SHARED COUNTER WITH LOCK

This version uses a global array and a shared ReentrantLock to prevent race conditions.
Each thread increments array values inside a nested loop, locking the array index before each access.

🧾 SHARED COUNTER WITH CLASS

Here, shared data (array + size) is encapsulated in a class without synchronization.
Multiple threads access and update the shared array, leading to race conditions.

🧾 SHARED COUNTER WITH CLASS AND LOCK

This version adds a lock inside the shared class to protect both inc() and get() methods.
It prevents concurrent access issues and improves correctness.

🧾 GLOBAL COUNTER WITH LOCK

This implementation shares a global counter and array.
Threads use a ReentrantLock to safely increment both, avoiding ArrayIndexOutOfBoundsException.

🧾 GLOBAL COUNTER WITHOUT LOCK

Similar to the previous, but with no locking.
Threads increment the shared counter and array directly — prone to race conditions but interesting for testing behavior.

🧾 GLOBAL COUNTER WITH LOCK IN SHARED CLASS

All access (get/set) is locked within the shared class.
Even so, timing issues can still cause problems. Great example of non-atomic multi-step operations.

