package com.trent.user.controller;

public class RedBlackTree {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	//根节点
	private TreeNode<Integer> root;
	
	public RedBlackTree() {
	}
	
	public TreeNode<Integer> getRoot() {
		return root;
	}
	
	//添加元素
	public void insert(Integer data) {
		//如果根节点为空，新建节点作为根节点，颜色为黑色
		if (root == null) {
			root = new TreeNode<Integer>(null, null, null, data, BLACK);
			return;
		}
		TreeNode<Integer> node = root;
		//新建的父节点
		TreeNode<Integer> parent;
		//查找要插入的位置
		while (true) {
			if (data.compareTo(node.data) == 0) {
				return;
			}
			parent = node;
			//如果data小于节点值，查询左子树
			boolean goLeft = data.compareTo(node.data) < 0;
			node = goLeft ? node.left : node.right;
			//如果左子树/右子树为空，则插入新值
			if (node == null) {
				if (goLeft) {
					parent.left = new TreeNode<Integer>(parent, null, null, data, RED);
				} else {
					parent.right = new TreeNode<Integer>(parent, null, null, data, RED);
				}
				//新插入节点后的处理
				fixAfterInsertion(parent.left == null ? parent.right : parent.left);
				break;
			}
		}
	}
	
	private void fixAfterInsertion(TreeNode<Integer> x) {
		x.color = RED;
		while (x != null && x != root && x.parent.color == RED) {
			TreeNode<Integer> xParent = x.parent;
			TreeNode<Integer> xGrandParent = xParent.parent;
			//如果x的父节点是x祖父节点的左子节点
			if (xParent == xGrandParent.left) {
				TreeNode<Integer> xUncle = xGrandParent.right;
				//情况1：叔叔节点也是红色
				if (xUncle != null && xUncle.color == RED) {
					xParent.color = BLACK;
					xUncle.color = BLACK;
					xGrandParent.color = RED;
					x = xGrandParent;
					continue;
				}
				//情况2：叔叔是黑色，且当前节点是右子节点
				if (x == xParent.right) {
					x = xParent;
					rotateLeft(x);
				}
				//情况3：叔叔是黑色，且当前节点是左子节点
				xParent = x.parent;
				xGrandParent = xParent.parent;
				xParent.color = BLACK;
				xGrandParent.color = RED;
				rotateRight(xGrandParent);
			} else {
				//若x的父节点是x祖父节点的右子节点,与上面的完全相反，本质是一样的
				TreeNode<Integer> xUncle = xGrandParent.left;
				if (xUncle != null && xUncle.color == RED) {
					xParent.color = BLACK;
					xUncle.color = BLACK;
					xGrandParent.color = RED;
					x = xGrandParent;
					continue;
				}
				if (x == xParent.left) {
					x = xParent;
					rotateRight(x);
				}
				xParent = x.parent;
				xGrandParent = xParent.parent;
				xParent.color = BLACK;
				xGrandParent.color = RED;
				rotateLeft(xGrandParent);
			}
		}
		root.color = BLACK;
	}
	
	//左旋
	private void rotateLeft(TreeNode<Integer> x) {
		TreeNode<Integer> r = x.right;
		x.right = r.left;
		if (r.left != null) {
			r.left.parent = x;
		}
		r.parent = x.parent;
		if (x.parent == null) {
			root = r;
		} else if (x.parent.left == x) {
			x.parent.left = r;
		} else {
			x.parent.right = r;
		}
		r.left = x;
		x.parent = r;
	}
	
	//右旋
	private void rotateRight(TreeNode<Integer> x) {
		TreeNode<Integer> l = x.left;
		x.left = l.right;
		if (l.right != null) {
			l.right.parent = x;
		}
		l.parent = x.parent;
		if (x.parent == null) {
			root = l;
		} else if (x.parent.right == x) {
			x.parent.right = l;
		} else {
			x.parent.left = l;
		}
		l.right = x;
		x.parent = l;
	}
	
	//定义节点
	private static class TreeNode<Integer> {
		TreeNode<Integer> parent;
		TreeNode<Integer> left;
		TreeNode<Integer> right;
		Integer data;
		boolean color;
		public TreeNode(TreeNode<Integer> parent, TreeNode<Integer> left, TreeNode<Integer> right, Integer data, boolean color) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.data = data;
			this.color = color;
		}
	}
}