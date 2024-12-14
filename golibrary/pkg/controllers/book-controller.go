package controllers

import (
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"go-bookstore/pkg/models"
	"go-bookstore/pkg/utils"
	"log"
	"net/http"
	"strconv"
)

func GetAllBooks(w http.ResponseWriter, r *http.Request) {
	newBook := models.GetAllBooks()
	res, _ := json.Marshal(newBook)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	_, err := w.Write(res)
	if err != nil {
		log.Fatal(err)
	}
}

func GetBookById(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	bookId := vars["bookId"]
	Id, Error := strconv.ParseInt(bookId, 0, 0)
	if Error != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Println("error while parsing")
	}
	bookDetails, _ := models.GetBookById(Id)
	res, _ := json.Marshal(bookDetails)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	_, err := w.Write(res)
	if err != nil {
		log.Fatal(err)
	}
}

func CreateBook(w http.ResponseWriter, r *http.Request) {
	newBook := &models.Book{}
	utils.ParseBody(r, newBook)
	b := newBook.CreateBook()
	res, _ := json.Marshal(b)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	_, err := w.Write(res)
	if err != nil {
		log.Fatal(err)
	}
}

func DeleteBook(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	bookId := vars["bookId"]
	Id, Error := strconv.ParseInt(bookId, 0, 0)
	if Error != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Println("error while parsing")
	}
	book := models.DeleteBookById(Id)
	res, _ := json.Marshal(book)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	_, err := w.Write(res)
	if err != nil {
		log.Fatal(err)
	}
}

func UpdateBook(w http.ResponseWriter, r *http.Request) {
	var updatedBook = &models.Book{}
	utils.ParseBody(r, updatedBook)
	vars := mux.Vars(r)
	bookId := vars["bookId"]
	Id, Error := strconv.ParseInt(bookId, 0, 0)
	if Error != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Println("error while parsing")
	}
	bookDetails, db := models.GetBookById(Id)
	if updatedBook.BookName != "" {
		bookDetails.BookName = updatedBook.BookName
	}
	if updatedBook.Author != "" {
		bookDetails.Author = updatedBook.Author
	}
	db.Save(&bookDetails)

	res, _ := json.Marshal(bookDetails)
	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	_, err := w.Write(res)
	if err != nil {
		log.Fatal(err)
	}
}
