import java.io.*;

public class ST {

    private static BufferedReader reader;
    public static List list = new List();
    private static Suspect [] topSusp;
    private static int i;
    private class TreeNode{
        Suspect item;
        TreeNode l;
        TreeNode r;
        TreeNode parent;
        int N;
    }

    private static double totalSavings = 0;
    private TreeNode root;
    private static List listForLastNames;

    public Suspect searchByAFM(int AFM) {
        TreeNode node= root;
        while(node.item.key()!=AFM){
            if (AFM < node.item.key()) {
                node = node.l;

            } else {
                node = node.r;
            }
            if (node == null)
                return null;

        }
        return node.item;
    }

    public List searchByLastname(String last_name) {
        listForLastNames = new List();
        TreeNode node= root;

        iterateLastName(node, last_name);

        return listForLastNames;

    }

    private void iterateLastName(TreeNode node, String wantedName){
        if(node!= null){
            if (node.item.getLast_name().equalsIgnoreCase(wantedName)){
                listForLastNames.add(node.item);
            }
            iterateLastName(node.l,wantedName);
            iterateLastName(node.r,wantedName);
        }

    }

    public void insert(Suspect item){
        TreeNode newNode = new TreeNode();
        newNode.item=item;

        if(root==null){
            root = new TreeNode();
            root.item=item;
        }else{
            TreeNode selectedNode = root;
            TreeNode parent;

            while(true){
                parent=selectedNode;
                newNode.parent=parent;
                if(item.key()<selectedNode.item.key()){
                    selectedNode=selectedNode.l;

                    if(selectedNode==null){
                        parent.l=newNode;
                        increaseN(newNode);
                        return;
                    }
                }else{
                    selectedNode=selectedNode.r;

                    if (selectedNode==null){
                        parent.r=newNode;
                        increaseN(newNode);
                        return;
                    }
                }
            }
        }
    }

    public void insert_at_root(Suspect item){

        root=insert_at_root(root,item,null);


    }

    private TreeNode insert_at_root(TreeNode current,Suspect item, TreeNode parent){
        if(current==null){
            TreeNode node = new TreeNode();
            node.item=item;
            node.parent=parent;
            return node;
        }

        if(item.key()<current.item.key()){
            current.l=insert_at_root(current.l,item,current);
            current=rotR(current);
        }else{
            current.r=insert_at_root(current.r,item,current);
            current=rotL(current);
        }
        current.N = root.N + 1 ;
        return current;
    }

    private TreeNode rotR(TreeNode current){

        TreeNode parent =current.parent;
        TreeNode child=current.l;
        if(parent==null){
            root=child;
        }else if(parent.l==current){
            parent.l=child;
        }else{
            parent.r=child;
        }

        child.parent=current.parent;
        current.parent=child;
        current.l=child.r;
        if(child.r!=null)
            child.r.parent=current;
        child.r=current;
        return child;

    }

    private TreeNode rotL(TreeNode current){
        TreeNode parent = current.parent;
        TreeNode child=current.r;
        if(parent==null){
            root=child;
        }else if(parent.l==current){
            parent.l=child;
        }else{
            parent.r=child;
        }

        child.parent=current.parent;
        current.parent=child;
        current.r=child.l;
        if(child.l!=null)
            child.l.parent=current;
        child.l=current;
        return child;
    }

    public void remove(Suspect item){
        TreeNode current= root;
        TreeNode parent= root;

        boolean leftChild=true;

        while(current.item.key()!=item.key()){
            parent=current;

            if(item.key()<current.item.key()){
                leftChild=true;
                current=current.l;
            }else{
                leftChild=false;
                current=current.r;
            }
        }
        if(current.l==null && current.r==null){
            if(current== root)
                root =null;
            else if(leftChild) {
                parent.l = null;
                decreaceN(current,null);
            }else {
                parent.r = null;
                decreaceN(current,null);
            }
        }else if(current.r==null){
            if(current== root)
                root =current.l;
            else if(leftChild) {
                parent.l = current.l;
                decreaceN(parent.l,null);
            }else {
                parent.r = current.l;
                decreaceN(parent.r,null);
            }
        }else if(current.l==null){
            if(current==null)
                root =current.r;
            else if(leftChild) {
                parent.l = current.r;
                decreaceN(parent.l,null);
            }else{
                    parent.r = current.r;
                decreaceN(parent.r,null);
            }
        }else{
            TreeNode replace=getReplacement(current);

            if(current== root)
                root =replace;
            else if(leftChild)
                parent.l=replace;
            else
                parent.r=replace;
            replace.l=current.l;
            decreaceN(replace,current);
        }

    }

    public TreeNode getReplacement(TreeNode deletedNode){
        TreeNode rParent=deletedNode;
        TreeNode replace=deletedNode;
        TreeNode current=deletedNode.r;

        while(current!=null){
            rParent=replace;
            replace=current;
            current=current.l;
        }

        if(replace!=deletedNode.r){
            rParent.l=replace.r;
            replace.r=deletedNode.r;
        }

        return  replace;

    }

    public void load(String filename){

        reader=null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file :(");
        }

        try{

            String line = reader.readLine();
            int i;
            char c;
            StringReader sr;
            while (line!=null) {
                i = 0;
                sr = new StringReader(line);
                c = (char)sr.read();
                i++;
                String afm = "";
                while (c!=' ') {
                    afm += c;
                    c = (char)sr.read();
                    i++;
                }
                int AFM = Integer.parseInt(afm.trim());
                c = (char)sr.read();
                i++;
                String firstName = "";
                while (c!=' ') {
                    firstName += c;
                    c =(char) sr.read();
                    i++;
                }
                firstName = firstName.trim();
                c = (char) sr.read();
                i++;

                String lastName = "";
                while (c!=' ') {
                    lastName += c;
                    c = (char)sr.read();
                    i++;
                }
                lastName = lastName.trim();
                c =(char) sr.read();
                i++;

                String savings = "";
                while (c!=' ') {
                    savings += c;
                    c =(char) sr.read();
                    i++;
                }
                int sav = Integer.parseInt(savings.trim());
                c = (char) sr.read();
                i++;

                String inc = "";
                while (i<=line.length()) {
                    inc += c;
                    c =(char) sr.read();
                    i++;

                }
                int income = Integer.parseInt(inc.trim());
                Suspect s =new Suspect(AFM, firstName, lastName, sav, income);
                insert(s);

                line = reader.readLine();
            }
        } catch (Exception E){
            System.out.println("Error!");
        }

    }

    public double getMeanSavings(){

        iterateSavings(root);

        double sav = totalSavings;
        int sus = root.N + 1;
        totalSavings = 0;

        return sav/sus;


    }

    public void printTopSuspects(PrintStream stream, int k) {

        i = 0;
        topSusp = new Suspect[root.N+1];
        iterateForTop(root);
        quickSort(0, topSusp.length-1);
        if (k<=root.N+1) {
            for (int j = 0; j < k; j++) {
                stream.println(topSusp[topSusp.length - j - 1].toString());
            }
        }else{
            for (int j = 0; j < root.N+1; j++) {
                stream.println(topSusp[topSusp.length - j - 1].toString());
            }
            System.out.println("The list had only these suspects.");
        }

    }

    private void quickSort(int left, int right){

        int l=left;
        int r=right;
        double pivot=topSusp[(int)left+(right-left)/2].getDifference();

        while(l<=r){

            while(topSusp[l].getDifference()<pivot){
                l++;
            }

            while(topSusp[r].getDifference()>pivot){
                r--;
            }

            if(l<=r){
                swap(l,r);
                l++;
                r--;
            }

            if(left<r)
                quickSort(left, r);
            if(l<right)
                quickSort(l, right);


        }
    }

    private void swap(int left, int right) {
        Suspect temp = topSusp[right];
        topSusp[right] = topSusp[left];
        topSusp[left] = temp;
    }

    private void iterateForTop(TreeNode node){
        if(node!=null){
            topSusp[i] = node.item;
            i++;
            iterateForTop(node.l);
            iterateForTop(node.r);
        }
    }

    private void iterateSavings(TreeNode node){

        if(node!= null){
            totalSavings += node.item.getSavings();
            iterateSavings(node.l);
            iterateSavings(node.r);
        }

    }

    public void printTreeByAFM(PrintStream stream){

        returnOrderedAFM(root);

        stream.println(list.toString());
        list = new List();

    }

    private void returnOrderedAFM(TreeNode current){

        if(current!=null){
            returnOrderedAFM(current.l);

            list.add(current.item);

            returnOrderedAFM(current.r);

        }

    }

    private void increaseN(TreeNode node){
        if(node.parent!=null){
            while(node!=root){
                node.parent.N=node.parent.N+1;
                node=node.parent;
            }
        }
    }

    private void decreaceN(TreeNode node,TreeNode deletedNode){
        if(node.parent!=null){
            while(node!=root){
                node.parent.N=node.parent.N-1;
                node=node.parent;
            }
        }
        if(deletedNode!=null) {
            node.N = deletedNode.N;
        }
    }


}
