"use client"

import React from "react";

type FileDownloadButtonProps = {
  file : MediaFile
}

const FileDownloadButton = ({file } : FileDownloadButtonProps) => {

  const getFile = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/files/${file.id}`);
      if (!response.ok) {
        throw new Error("Failed to get file");
      }
      const data: MediaFileDownload = await response.json();

      // Decode the Base64-encoded file content to binary data
      const byteCharacters = atob(data.fileContent || "");
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      const blob = new Blob([byteArray], { type: "application/pdf" });
      const url = URL.createObjectURL(blob);

      const a = document.createElement("a");
      a.href = url;
      a.download = `${file.fileName}`;
      a.click();

      URL.revokeObjectURL(url);

    } catch (error) {
      console.error("Error downloading file:", error);
    }
  };


  return (
    <button
      type="button"
      className="inline-block rounded bg-indigo-600 px-4 py-2 text-xs font-medium text-white hover:bg-indigo-700"
      onClick={getFile}
    >
      <svg
        className="w-4 h-4 text-gray-800 dark:text-white"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 16 18"
      >
        <path
          stroke="currentColor"
          strokeLinecap="round"
          strokeLinejoin="round"
          strokeWidth="2"
          d="M8 1v11m0 0 4-4m-4 4L4 8m11 4v3a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-3"
        />
      </svg>
    </button>
  );
};

export default FileDownloadButton;
