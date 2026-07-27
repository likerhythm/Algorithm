/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode cursor = result;
        int c = 0;
        while (true) {
            int sum = 0;
            if (l1 == null && l2 == null) {
                break;
            }
            if (l1 == null) {
                sum = l2.val + c;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val + c;
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val + c;
                l1 = l1.next;
                l2 = l2.next;
            }
            c = sum / 10;
            cursor.val = sum % 10;
            if (l1 != null || l2 != null) {
                cursor.next = new ListNode();
                cursor = cursor.next;
            } else {
                if (c > 0) {
                    cursor.next = new ListNode(c);
                    break;
                }
            }
        }
        return result;
    }
}