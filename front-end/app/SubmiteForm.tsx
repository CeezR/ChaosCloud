"use client";
import axios from "axios";
import React, { useRef, useState } from "react";

type requestDTO = {
  name: string;
  content: any;
};

const SubmiteForm = () => {
  const [isSubmitBtnVisable, setIsSubmiteBtnVisable] = useState(false);
  const fileNameRef = useRef<HTMLSpanElement>(null);

  const handleSubmit = async (event: React.ChangeEvent<HTMLInputElement>) => {
    try {

      if (!event.target.files || event.target.files.length === 0) {
        throw new Error('No file selected');
      }

      const file = event.target.files[0];

      // Convert the file to a byte array
      const reader = new FileReader();
      reader.readAsArrayBuffer(file);
      reader.onload = async () => {
        const fileContent = new Uint8Array(reader.result as ArrayBuffer);

        const data = {
          fileName: file.name,
          fileContent: Array.from(fileContent), // Convert Uint8Array to a regular array
        };

        const response = await axios.post("http://localhost:8080/api/files", data);
        if (!response.status) {
          throw new Error("Failed to Upload File");
        }
    
        const newFile: MediaFile = await response.data;
        //setCompanies((prevCompanies) => [...prevCompanies, newCompany]);
        console.log(newFile);


      };
    } catch (error) {
      console.error("Error uploading file:", error);
    }
  };

  return (
    <label className="inline-flex items-center justify-center gap-1.5 rounded-lg border border-gray-200 bg-white px-5 py-3 text-gray-500 transition hover:text-gray-700 focus:outline-none focus:ring">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        className="h-5 w-5"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        strokeWidth="2"
      >
        <path
          stroke="currentColor"
          strokeLinecap="round"
          strokeLinejoin="round"
          strokeWidth="2"
          d="M15 6V2a.97.97 0 0 0-.933-1H5.828a2 2 0 0 0-1.414.586L1.586 4.414A2 2 0 0 0 1 5.828V18a.969.969 0 0 0 .933 1H14a1 1 0 0 0 1-1M6 1v4a1 1 0 0 1-1 1H1m6 6h9m-1.939-2.768L16.828 12l-2.767 2.768"
        />
      </svg>
      <span className="text-sm font-medium">Upload</span>
      <input
        type="file"
        id="fileInput"
        name="fileInput"
        accept=".pdf, .jpg, .jpeg, .png, .txt, .docx"
        className="hidden"
        onChange={handleSubmit}
      />
    </label>
  );
};

export default SubmiteForm;
