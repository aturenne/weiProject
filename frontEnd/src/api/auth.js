import { ref } from 'vue'

const isAuthenticated = ref(false) //global state that tracks whether the user is logged in

const userRole = ref('') //Stores 'crew' or 'admin'

const login = async (username, password) => {
  //Simulate a successful login
  isAuthenticated.value = true
    userRole.value = username === 'adminUser' ? 'admin' : 'crew' //set userRole based on username
}

const logout = async () => {
  //Simulate a successful logout
  isAuthenticated.value = false
  userRole.value = ''
}

const getUserRole = () => {
  return userRole.value
}

export { isAuthenticated, login, logout, getUserRole }