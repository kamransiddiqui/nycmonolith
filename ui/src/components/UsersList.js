import React from 'react'

import User from './User'

const UsersList = (props) => {

  return (
    <div id="userPage">
      <h2>User Page</h2>
      {
        props.users.map((user, index) => {
          return (
            <User
              user={user}
              key={index}
              index={index}
              deleteUser={props.deleteUser}
              handleUserChange={props.handleUserChange}
              updateUser={props.updateUser} />
          )
        })
      }
    </div>
  )
}

export default UsersList