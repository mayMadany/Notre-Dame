//
//  SchoolTermSection.swift
//  ETSMobile
//
//  Created by Emmanuel Proulx on 2018-12-13.
//  Copyright © 2018 ApplETS. All rights reserved.
//

import UIKit

class SchoolTermSection: UITableViewCell, UICollectionViewDataSource, UICollectionViewDelegate {
    
    let courseArray = ["LOG121", "LOG240", "COM115", "ING150","ING150","ING150"]
    
    @IBOutlet weak var collectionView: UICollectionView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return courseArray.count
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "courseCell", for: indexPath) as? CourseCell {
            cell.displayContent(title: courseArray[indexPath.row])
            print(courseArray[indexPath.row])
            return cell
        }
        return UICollectionViewCell()
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize{
        let size = CGSize(width: 260, height: 260)
        return size
    }
    


}
