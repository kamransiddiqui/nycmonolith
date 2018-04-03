import React from 'react'

const User = props => {
  return(
    <div>
      <div id={`user-${props.user.id}`} data-user-display>
        <div id={`user-${props.user.id}-user-name`}>
          {props.user.userName}
        </div>
        <div id={`user-${props.user.id}-first-name`}>
          {props.user.firstName}
        </div>
        <div id={`user-${props.user.id}-last-name`}>
          {props.user.lastName}
        </div>
        <div id={`user-${props.user.id}-email`}>
          {props.user.email}
        </div>
        <div id={`user-${props.user.id}-job`}>
          {props.user.job}
        </div>
        <button
          id={`delete-user-${props.user.id}`}
          onClick={() => props.deleteUser(props.user.id, props.index)}>
          Delete
        </button>
      </div>
    </div>
  );
}

export default User