package code.adt.queue.simulation;

import core.queue.Queue;

public class InfiniteQueue extends Component {
    private final Queue<Atom> queue = Queue.fromSinglyLinkedListDoubleReference();

    public InfiniteQueue(Clock clock, ChooseOut chooseOut) {
        super(chooseOut);
        clock.addListeners(new Runnable() {
            @Override
            public void run() {
                if (!InfiniteQueue.this.queue.isEmpty()) {
                    if (InfiniteQueue.this.tryExit(InfiniteQueue.this.queue.peek())) {
                        InfiniteQueue.this.queue.dequeue();
                    }
                }
            }
        });
    }

    public InfiniteQueue(Clock clock) {
        this(clock, AnyAvailableChoose.instance);
    }

    @Override
    public void enter(Atom atom) {
        this.onEnter(atom);
        if (!this.tryExit(atom)) {
            this.queue.enqueue(atom);
        }
    }

    @Override
    public boolean canEnter() {
        return true;
    }

    public int getSize() {
        return this.queue.size();
    }
}