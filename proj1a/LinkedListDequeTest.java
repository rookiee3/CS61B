/**
 * 执行一些基本的链表测试。
 */
public class LinkedListDequeTest {
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() 返回了 " + actual + "，但预期是：" + expected);
			return false;
		}
		return true;
	}

	/* 用于打印大小检查的实用方法。 */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() 返回了 " + actual + "，但预期是：" + expected);
			return false;
		}
		return true;
	}

	/* 根据测试是否通过打印相应的消息。 */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("测试通过!\n");
		} else {
			System.out.println("测试失败!\n");
		}
	}

	/** 添加一些元素到列表中，检查 isEmpty() 和 size() 是否正确，最后打印结果。 */
	public static void addIsEmptySizeTest() {
		System.out.println("运行添加/isEmpty/size测试。");
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
		boolean passed = checkEmpty(true, lld1.isEmpty());
		lld1.addFirst("front");

		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("打印链表：");
		lld1.printDeque();

		printTestStatus(passed);
	}

	/** 添加一个元素，然后删除一个元素，并确保之后链表为空。 */
	public static void addRemoveTest() {
		System.out.println("运行添加/删除测试。");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
	}

	public static void getTest() {
		System.out.println("运行 get 测试。");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

		// 双向链表大小为0
		if (lld1.get(0) == null) {
			System.out.println("案例1通过");
		}

		// 索引大于实际大小
		lld1.addFirst(1);
		lld1.addFirst(2);
		if (lld1.get(2) == null) {
			System.out.println("案例2通过");
		}

		// 正常情况
		if (lld1.get(0).equals(2)) {
			System.out.println("案例3通过");
		}
	}

	public static void getRecursiveTest() {
		System.out.println("运行 getRecursive 测试。");
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

		// 双向链表大小为0
		if (lld1.getRecursive(0) == null) {
			System.out.println("案例1通过");
		}

		// 索引大于实际大小
		lld1.addFirst(1);
		lld1.addFirst(2);
		if (lld1.getRecursive(2) == null) {
			System.out.println("案例2通过");
		}

		// 正常情况
		lld1.addFirst(3);
		lld1.addFirst(4);
		if (lld1.getRecursive(0).equals(4)) {
			System.out.println("案例3通过");
		}
		if (lld1.getRecursive(1).equals(3)) {
			System.out.println("案例4通过");
		}
		if (lld1.getRecursive(3).equals(1)) {
			System.out.println("案例5通过");
		}
	}

	public static void main(String[] args) {
		System.out.println("运行测试。\n");
		addIsEmptySizeTest();
		addRemoveTest();
		getTest();
		getRecursiveTest();
	}
}
