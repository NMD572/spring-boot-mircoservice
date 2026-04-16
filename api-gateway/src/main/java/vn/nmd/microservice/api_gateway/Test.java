package vn.nmd.microservice.api_gateway;

import java.util.Arrays;

public class Test {
        public int findDuplicate(int[] nums) {
            // Phase 1: Cycle Detection --> Finding the intersection point in the cycle
            int tortoise = nums[0];
            int hare = nums[0];

            // Move tortoise by 1 step and hare by 2 steps
            do {
                tortoise = nums[tortoise];
                hare = nums[nums[hare]];
            } while (tortoise != hare);

            // Phase 2: Finding the entrance to the cycle (the duplicate)
            // Reset tortoise to the start
            tortoise = nums[0];

            // Move both at the same speed (1 step) until they meet
            // (because they need to go the same distance to meet at the entrance to the cycle)
            while (tortoise != hare) {
                tortoise = nums[tortoise];
                hare = nums[hare];
            }

            return tortoise;
        }






}
