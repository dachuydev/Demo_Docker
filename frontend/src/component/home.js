import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/products'); // Chuyển hướng đến ProductList
  };

  return (
    <div>
      <h1>Welcome to Home</h1>
      <button onClick={handleClick}>Go to Product List</button>
    </div>
  );
}

export default Home;
