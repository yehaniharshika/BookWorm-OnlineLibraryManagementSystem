package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LibraryBranch {
    @Id
    private String branchID;
    private String branchName;
    private String location;

    @ManyToOne
    @JoinColumn(name = "adminID")
    private Admin admin;

    @OneToMany(mappedBy = "libraryBranch")
    private List<Book> bookList;


    public LibraryBranch(Admin admin, List<Book> bookList, String branchID, String branchName, String location) {
        this.admin = admin;
        this.bookList = bookList;
        this.branchID = branchID;
        this.branchName = branchName;
        this.location = location;
    }

    public LibraryBranch(String branchID, String branchName, String location, Admin adminID) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.location = location;
        this.admin = adminID;

    }

    public LibraryBranch() {

    }

    public Admin getAdmin(){
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
