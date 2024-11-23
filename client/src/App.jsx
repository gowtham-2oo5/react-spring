import React, { useEffect, useState } from "react";
import axios from "axios";
import { useForm } from "react-hook-form";

const App = () => {
  const [users, setUsers] = useState([]);
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [editingUser, setEditingUser] = useState(null); // To hold the user being edited
  const { register, handleSubmit, reset } = useForm();

  // Function to load users
  const loadUsers = async () => {
    try {
      const response = await axios.get("/api/users");
      setUsers(response.data);
    } catch (error) {
      console.error("Error loading users:", error);
    }
  };

  // Function to handle adding a new user
  const addUser = async (data) => {
    try {
      await axios.post("/api/users", data);
      setIsAddModalOpen(false);
      reset();
      loadUsers();
    } catch (error) {
      console.error("Error adding user:", error);
    }
  };

  // Function to handle updating a user
  const updateUser = async (data) => {
    try {
      await axios.put(`/api/users/update/${editingUser.id}`, data);
      setIsEditModalOpen(false);
      setEditingUser(null);
      reset();
      loadUsers();
    } catch (error) {
      console.error("Error updating user:", error);
    }
  };

  // Function to handle deleting a user
  const deleteUser = async (id) => {
    try {
      await axios.delete(`/api/users/delete/${id}`);
      loadUsers();
    } catch (error) {
      console.error("Error deleting user:", error);
    }
  };

  useEffect(() => {
    loadUsers();
  }, []);

  return (
    <div className="p-4">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-lg font-bold">User Management</h1>
        <button
          className="bg-blue-500 text-white px-4 py-2 rounded"
          onClick={() => setIsAddModalOpen(true)}
        >
          Add User
        </button>
      </div>

      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr>
            <th className="border border-gray-300 px-4 py-2">ID</th>
            <th className="border border-gray-300 px-4 py-2">Name</th>
            <th className="border border-gray-300 px-4 py-2">Email</th>
            <th className="border border-gray-300 px-4 py-2">Password</th>
            <th className="border border-gray-300 px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td className="border border-gray-300 px-4 py-2">{user.id}</td>
              <td className="border border-gray-300 px-4 py-2">{user.name}</td>
              <td className="border border-gray-300 px-4 py-2">{user.email}</td>
              <td className="border border-gray-300 px-4 py-2">
                {user.password}
              </td>
              <td className="border border-gray-300 px-4 py-2">
                <button
                  className="bg-green-500 text-white px-2 py-1 rounded mr-2"
                  onClick={() => {
                    setEditingUser(user);
                    setIsEditModalOpen(true);
                  }}
                >
                  Edit
                </button>
                <button
                  className="bg-red-500 text-white px-2 py-1 rounded"
                  onClick={() => deleteUser(user.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Add User Modal */}
      {isAddModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded shadow-lg">
            <h2 className="text-lg font-bold mb-4">Add User</h2>
            <form onSubmit={handleSubmit(addUser)}>
              <div className="mb-4">
                <label className="block mb-1">Name</label>
                <input
                  {...register("name")}
                  className="w-full px-3 py-2 border border-gray-300 rounded"
                  required
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1">Email</label>
                <input
                  {...register("email")}
                  type="email"
                  className="w-full px-3 py-2 border border-gray-300 rounded"
                  required
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1">Password</label>
                <input
                  {...register("password")}
                  type="password"
                  className="w-full px-3 py-2 border border-gray-300 rounded"
                  required
                />
              </div>
              <div className="flex justify-end">
                <button
                  type="button"
                  className="bg-gray-500 text-white px-4 py-2 rounded mr-2"
                  onClick={() => setIsAddModalOpen(false)}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                  Add
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Edit User Modal */}
      {isEditModalOpen && editingUser && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-white p-6 rounded shadow-lg">
            <h2 className="text-lg font-bold mb-4">Edit User</h2>
            <form onSubmit={handleSubmit((data) => updateUser({ ...data }))}>
              <div className="mb-4">
                <label className="block mb-1">New Password</label>
                <input
                  {...register("password")}
                  type="password"
                  className="w-full px-3 py-2 border border-gray-300 rounded"
                  required
                />
              </div>
              <div className="flex justify-end">
                <button
                  type="button"
                  className="bg-gray-500 text-white px-4 py-2 rounded mr-2"
                  onClick={() => setIsEditModalOpen(false)}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="bg-green-500 text-white px-4 py-2 rounded"
                >
                  Save Changes
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default App;
