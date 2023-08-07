"use client";
import React from "react";
import { formatFileSize } from "./helper";
import FileDownloadButton from "./FileDownloadButton";

type FileListProps = {
  mediaFiles: MediaFile[];
};

const FileList = ({ mediaFiles }: FileListProps) => {
  return (
    <>
      <div className="overflow-x-auto">
        <table className="min-w-full divide-y-2 divide-gray-200 bg-white text-sm text-left">
          <thead>
            <tr>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                Name
              </th>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                type
              </th>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                Posted By
              </th>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                Date Posted
              </th>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                File Size
              </th>
              <th className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                Download
              </th>
            </tr>
          </thead>

          <tbody className="divide-y divide-gray-200">
            {mediaFiles.map((file, index) => {
              return (
                <tr key={index}>
                  <td className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                    {file.fileName.substring(0, file.fileName.lastIndexOf("."))}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.fileExtension}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.postedBy}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.createdDate.substring(0, file.createdDate.lastIndexOf("T"))}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {formatFileSize(file.fileSize)}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2">
                    <FileDownloadButton file={file}/>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default FileList;
