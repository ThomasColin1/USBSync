
/*import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

class Main {
    public static void main(String[] argv) throws IOException {
        File fichier = new File("C://Users//Colin//Documents//test");
        System.out.println(fichier.getName() + " : " + fichier.getPath());
        File src = new File("C://Users//Colin//Documents//file1");
        File dir = new File("C://Users//Colin//Documents//file2");
        Functions.utilCopier(src, dir);

    }
}
*/

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class Main{
    public static void main (String[] args) throws IOException{
        JFramePrinc principale = new JFramePrinc();
        principale.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Functions.utilCopier(new File("C://Users//Colin//Documents//file1"), new File("D://sync"));
        File hey = new File("C://Users//Colin//Documents//file1//qsd.txt");
        File ho = new File("C://Users//Colin//Documents//file2//qsd.txt");
        BasicFileAttributes attr = Files.readAttributes(hey.toPath(), BasicFileAttributes.class);
        BasicFileAttributes attr2 = Files.readAttributes(ho.toPath(), BasicFileAttributes.class);
        System.out.println(attr.lastModifiedTime()+"            ;         "+ attr2.lastModifiedTime());

        System.out.println(hey.lastModified()+"            ;         "+ ho.lastModified());

        System.out.println(hey.lastModified()>ho.lastModified());
    }
}
