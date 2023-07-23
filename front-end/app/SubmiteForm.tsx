"use client"
import React from 'react'


type requestDTO = {
  name: string,
  content: any
};

const SubmiteForm = () => {
  const handleSubmite = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    // Get the file input element
    const fileInput = event.currentTarget.fileInput;

    // Check if any file is selected
    if (fileInput.files && fileInput.files.length > 0) {
      // Get the first selected file (you can iterate through files if multiple files are allowed)
      const file = fileInput.files[0];

      // Create a new Blob object representing the file content
      const fileBlob = new Blob([file]);

      // Create the request object with the file name and Blob object
      const request = {
        fileName: file.name,
        fileContent: fileBlob,
      };

      try {
        const response = await fetch('http://localhost:8080/api/files', {
          method: 'POST',
          body: fileBlob, // Send the Blob object directly as the request body
        });

        if (response.ok) {
          console.log('File uploaded successfully!');
        } else {
          console.error('Failed to upload the file.');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    } else {
      console.error('No file selected.');
    }
  }

  return (
    <form onSubmit={handleSubmite}>
      <label htmlFor="fileInput">Choose a file:</label>
      <input type="file" id="fileInput" name="fileInput" accept=".pdf, .jpg, .jpeg, .png, .txt, .docx" />
      <button type="submit">Submit</button>
    </form>
  )
}

export default SubmiteForm;


