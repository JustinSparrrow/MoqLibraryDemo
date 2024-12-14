package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"go-bookstore/pkg/routes"
	"log"
	"net/http"
)

func main() {
	r := mux.NewRouter()
	routes.RegisterBookStoreRoutes(r)
	http.Handle("/", r)
	fmt.Println("port: 9090")
	log.Fatal(http.ListenAndServe(":9090", nil))
}
