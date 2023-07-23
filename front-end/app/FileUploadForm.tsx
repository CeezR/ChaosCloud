"use client"

import React from 'react';
import axios from 'axios';

const FileUploadForm = () => {
  
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    try {
      const fileInput = document.getElementById('fileInput') as HTMLInputElement;
      const file = fileInput.files[0];

      // Convert the file to a byte array
      const reader = new FileReader();
      reader.readAsArrayBuffer(file);
      reader.onload = () => {
        const fileContent = new Uint8Array(reader.result as ArrayBuffer);

        const data = {
          fileName: file.name,
          fileContent: Array.from(fileContent), // Convert Uint8Array to a regular array
        };

        // Send the file data in the request body
        const response = axios.post('http://localhost:8080/api/files', data)
      };
    } catch (error) {
      console.error('Error uploading file:', error);
      // Handle the error state accordingly.
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="fileInput">Choose a file:</label>
        <input
          type="file"
          id="fileInput"
          accept=".pdf, .doc, .docx, .jpg, .jpeg, .png" // Add accepted file types here.
        />
      </div>
      <button type="submit">Upload File</button>
    </form>
  );
};

export default FileUploadForm;




