package domain.db;

import domain.model.Category;
import domain.Exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryDB {
    private ArrayList<Category> categories;
    private File f;

    public CategoryDB(){
        categories = new ArrayList<>();
        f = new File("1718_selfAssess_opgave/Categories.txt");
        if (f.isDirectory() || !f.exists()) {
            throw new dbException("File niet gevonden");
        }
        fill();
    }
    private void fill(){
        try {
            Scanner scan = new Scanner(f, "UTF-8");
            while (scan.hasNextLine()) {
                Scanner line = new Scanner(scan.nextLine());
                line.useDelimiter(";");
                String cat = line.next();
                String desc = line.next();
                String sup = "";
                if(line.hasNext()){
                    sup = line.next();
                }
                if(sup.equals("")){
                    Category category = new Category(cat,desc);
                    categories.add(category);
                    System.out.println(cat+" added without sup");
                }else{
                    Category category = new Category(cat,desc,sup);
                    categories.add(category);
                    System.out.println(cat+" added with sup");
                }
            }
        } catch (Exception e) {
            throw new dbException("Fout bij inlezen bestand");
        }
    }

    @Override
    public String toString(){
        String res = "";
        for(Category c:categories){
            res += c.getName() + " | "+c.getDescription()+
                    " | " +c.getSupercategory()+ "\n";
        }
        return res;
    }

    public ArrayList<Category> getAll(){
        return (ArrayList<Category>)categories;
    }
}