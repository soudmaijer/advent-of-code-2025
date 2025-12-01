class CircularList<T>(private val list: List<T>) {
    private var currentPosition = 0

    /**
     * Index is 0 based, moving to position 0 means moving to the first element in the list.
     */
    fun moveToPosition(pos: Int) {
        if(pos >= list.size) throw IndexOutOfBoundsException("Position: $pos is longer than list: ${list.size}")
        currentPosition = pos
    }

    /**
     * Returns the elements at the currentPosition.
     */
    fun currentElement(): T {
        return list[currentPosition]
    }

    /**
     * Returns the elements at the currentPosition.
     */
    fun currentPosition(): Int {
        return currentPosition
    }

    /**
     * Steps forward for the given number of steps and returns new (current) position.
     */
    fun stepForward(steps: Int = 1): Int {
        if(list.size <= 1) {
            currentPosition = 0
        }
        else if(currentPosition + steps < list.size) {
            currentPosition += steps
        }
        else if( currentPosition + steps >= list.size) {
            currentPosition = (currentPosition + steps) % list.size
        }
        else {
            throw IllegalStateException("Should not happen!")
        }
        if(currentPosition() == list.size || currentPosition() < 0) {
            throw IllegalStateException("Should not happen!")
        }
        return currentPosition
    }

    /**
     * Steps back for the given number of steps and returns new (current) position.
     */
    fun stepBack(steps: Int = 1): Int {
        val oldPosition = currentPosition

        if(list.size <= 1) {
            currentPosition = 0
        }
        else if(currentPosition - (steps % list.size) >= 0 ) {
            currentPosition -= (steps % list.size)
        }
        else if( currentPosition - steps < 0) {
            currentPosition = list.size - ((steps - currentPosition) % list.size)
        }
        else {
            throw IllegalStateException("Should not happen!")
        }
        if(currentPosition() == list.size || currentPosition() < 0) {
            throw IllegalStateException("Should not happen!")
        }

        return currentPosition
    }
}