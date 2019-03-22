package code.leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestConsecutiveSequenceTest {
    private LongestConsecutiveSequence longestConsecutiveSequence;

    @Before
    public void setUp() throws Exception {
        this.longestConsecutiveSequence = new LongestConsecutiveSequence();
    }

    @Test
    public void longestConsecutive() {
        System.out.println(this.longestConsecutiveSequence.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}