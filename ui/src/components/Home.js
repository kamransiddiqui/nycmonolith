import React from 'react'
import { Link } from 'react-router-dom'

const Home = (props) => {

  return (
    <div id='navbar'>
      <h2>NYC Monolith</h2>
      <h3>New York City Search Engine</h3>
      <Link to="/users" id="users-link"> Users </Link>
      <Link to="/newuser"> Create username </Link>
    </div>
  )
}

export default Home