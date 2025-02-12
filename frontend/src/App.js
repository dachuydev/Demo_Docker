import React from 'react';
import './App.css';
import ProductList from './component/ProductList';
import Home from './component/home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<ProductList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
