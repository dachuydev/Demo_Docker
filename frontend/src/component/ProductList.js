import React, { useEffect, useState } from "react";

const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/product/page")
            .then((response) => response.json())
            .then((data) => {
                console.log("Received data:", data);
                setProducts(data.content);
            })
            .catch((error) => {
                console.error("Error fetching products:", error);
            });
    }, []);

    return (
        <div className="container mt-4">
            <h4>List product</h4>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Quốc gia</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map((product, index) => (
                        <tr key={product.id}>
                            <td>{index + 1}</td>
                            <td>{product.productName}</td>
                            <td>{product.price}</td>
                            <td>{product.country}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProductList;
