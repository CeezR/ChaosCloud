"use client";
import React from "react";

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
            {mediaFiles.map((file) => {
              return (
                <tr>
                  <td className="whitespace-nowrap px-4 py-2 font-medium text-gray-900">
                    {file.name}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.fileExtension}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.postedBy}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    {file.createdDate}
                  </td>
                  <td className="whitespace-nowrap px-4 py-2 text-gray-700">
                    5 KB
                  </td>
                  <td className="whitespace-nowrap px-4 py-2">
                    <button
                      type="button"
                      className="inline-block rounded bg-indigo-600 px-4 py-2 text-xs font-medium text-white hover:bg-indigo-700"
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
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="M8 1v11m0 0 4-4m-4 4L4 8m11 4v3a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-3"
                        />
                      </svg>
                    </button>
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
