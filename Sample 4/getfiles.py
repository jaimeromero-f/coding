#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Apr  7 11:57:15 2023

@author: jaimeromero
"""
import requests
from bs4 import BeautifulSoup
import os
import pdb
import time

#pdb.set_trace()

def get_html(initial_year, final_year):

    #entries per page
    index_of_s1 = 1
    count_of_entries_per_page = 80
    
    while index_of_s1 < 4000:
    
        # base url
        base_url = 'https://www.sec.gov/cgi-bin/srch-edgar'
        
        # Dsearch params
        params = {
            'text': 'FORM-TYPE=S-1',
            'first': '2021',
            'last': '2023',
            'count': count_of_entries_per_page,
            'start': index_of_s1,
        }
        
        headers = {
            'User-Agent': 'Jaime Romero jhr2153@columbia.edu',
            'Accept-Encoding': 'gzip, deflate',
            'Host': 'www.sec.gov'
        }
        
        # html response
        response = requests.get(base_url, params=params, headers=headers)
        
        # parser
        soup = BeautifulSoup(response.text, 'html.parser')
        
        # all links in the company's S-1/A document
        company_links = soup.find_all('a', href=True)
        
        # extract the links to the HTML versions of the S-1/A form
        html_links = [link['href'] for link in company_links 
                      if link['href'].startswith('/Archives/edgar/data/') 
                      and link['href'].endswith('.htm')]
        
        # output directory
        output_dir = './s1_filings/'
        
        # create the output directory if it does not exist
        if not os.path.exists(output_dir):
            os.makedirs(output_dir)
        
        # loop through the links and download the corresponding files
        for link in html_links:
            get_individual(link, headers, output_dir)

            
        # count update
        index_of_s1 = index_of_s1 + count_of_entries_per_page
        
        
def get_individual(link, headers, output_dir):
    # url
    file_url = 'https://www.sec.gov' + link

    # download the file
    response = requests.get(file_url, headers=headers)
    
    # parse once again
    soup = BeautifulSoup(response.text, 'html.parser')
    
    # get S1 file
    s1_complement = soup.find_all('a', href=True)[8]
    
    if 'cgi-bin' in s1_complement['href']:
        s1_complement = soup.find_all('a', href=True)[9]
    
    # Turn into complement
    if s1_complement is not None:
        href = s1_complement['href']
    
    # create link
    s1_link = 'https://www.sec.gov' + href
    
    time.sleep(0.2)
    
    # find S1 response
    response_s1 = requests.get(s1_link, 'html.parser', headers=headers)


    # get the filename
    filename = s1_link.split('/')[-2] + '.html'
    
    
    # save the file to disk
    with open(os.path.join(output_dir, filename), 'wb') as f:
        f.write(response_s1.content)
        
    # sleep
    time.sleep(0.2)

    print(f'{filename} downloaded successfully.')


################# MAIN METHOD ######################

if __name__ == "__main__":
    get_html('2021', '2022')
    get_html('2017', '2020')
    get_html('2014', '2016')
