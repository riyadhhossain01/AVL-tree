/**
 * Created by riyadh hossain on 7/19/2017.
 */


import java.util.Scanner;

class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

class AVLTree {

    Node root;
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node j) {
        Node i = j.left;
        Node SUBTREE = i.right;

        // to rotate
        i.right = j;
        j.left = SUBTREE;

        // Update heights
        j.height = max(height(j.left), height(j.right)) + 1;
        i.height = max(height(i.left), height(i.right)) + 1;

        return i;
    }
    // to rotate left
    Node leftRotate(Node i) {
        Node j = i.right;
        Node SUBTREE = j.left;

        // to rottate right
        j.left = i;
        i.right = SUBTREE;

        //  Update heights
        i.height = max(height(i.left), height(i.right)) + 1;
        j.height = max(height(j.left), height(j.right)) + 1;
        return j;
    }

    // balance height
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int key) {

        //insert
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;
        node.height = 1 + max(height(node.left),
                height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    //Preorder recursion function
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    //Inorder recursion function
    void inOrder(Node node) {
        if(node != null){
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder((node.right));
        }
    }
    // Postorder recursion function
    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        System.out.println("Enter keys to contruct AVL tree or enter -1 to quit");
        Scanner reader = new Scanner(System.in);
        int x = reader.nextInt();
        while(x!=-1){
            tree.root = tree.insert(tree.root, x);
            x = reader.nextInt();
        }

        System.out.println("Preorder Travese");
        tree.preOrder(tree.root);
        System.out.println("\nInorder Traverse");
        tree.inOrder(tree.root);
        System.out.println("\nPostorder Travers ");
        tree.postOrder(tree.root);
    }
}


